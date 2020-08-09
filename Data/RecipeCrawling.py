from selenium import webdriver
import time
import openpyxl as xl

# csv 파일 열기
f = xl.Workbook()
sheet = f.active
sheet.append([
    "음식명", "인분", "조리시간", "난이도", "재료", "동영상url",
    "조리과정" # 조리과정 리스트 형식으로 한꺼번에 저장하기로 함 (2020.08.09)
    # ,"order1", "img1", "order2", "img2", "order3", "img3", "order4", "img4", "order5", "img5",
    # "order6", "img6", "order7", "img7", "order8", "img8", "order9", "img9", "order10", "img10"
    # ,"comment1", "comment2", "comment3", "comment4", "comment5", "comment6" # 댓글 및 후기는 수집 안하기로 함 (2020.08.09)
])

# 크롬창(웹드라이버) 열기
driver = webdriver.Chrome("./chromedriver")

# 만개의 레시피 사이트 접속하기
# 추천순, 페이지당 40개 리스트 존재함 (전체 14만여개 데이터)
url = "https://www.10000recipe.com"

# pageNum = 1 
for pageNum in range(1, 201): # 8000개 수집 예정 (200 페이지)
    driver.get(url + "/recipe/list.html?order=reco&page=" + str(pageNum))
    # time.sleep(2)

    # recipe id(url) 40개 수집하기
    dishes = driver.find_elements_by_css_selector(".common_sp_link")
    # print(dishes)

    # 개별 recipe id(url) 찾아 들어가서 정보 크롤링하기
    for dish in dishes:
        dishUrl = dish.get_attribute('href')
        driver.get(dishUrl)

        # "음식명", "인분", "조리시간", "난이도", "재료", "동영상url",
        
        dishName = driver.find_element_by_css_selector(".view2_summary h3").text
        
        peopleCount = driver.find_element_by_css_selector(".view2_summary_info1").text
        
        time = driver.find_element_by_css_selector(".view2_summary_info2").text
        
        level = driver.find_element_by_css_selector(".view2_summary_info3").text
        
        ingredientInfo = driver.find_elements_by_css_selector(".ready_ingre3 .case1 li")
        ingredientList = []
        for i in ingredientInfo:
            text = i.text.split('\n')
            ingredientList.append(text[0] + "(" + text[1] + ")")
        ingredients = ", ".join(ingredientList)
        # print(ingredients)

        # videoUrl
        try:
            videoUrl = driver.find_element_by_css_selector(".rmc_video_info a.rmc_title").get_attribute("href")
        except:
            try:
                clickButton = driver.find_element_by_css_selector(".ytp-share-icon")
                clickButton.click()
                videoUrl = driver.find_element_by_css_selector("a.ytp-share-panel-link").get_attribute("href")
            except:
                videoUrl = ""

        # 조리과정
        # 레시피 단계별 text, image 리스트로 받아오기
        orders = []
        orderTexts = driver.find_elements_by_css_selector(".view_step .media-body")
        orderImages = driver.find_elements_by_css_selector(".view_step .media-right img")
        for idx in range(len(orderTexts)):
            text = orderTexts[idx].text
            image = orderImages[idx].get_attribute("src")
            orders += [text, image]
        # print(orders)

        # sheet.append(["음식명", "인분", "조리시간", "난이도", "재료", "동영상url", "조리과정"])
        sheet.append([dishName, peopleCount, time, level, ingredients, videoUrl, ", ".join(orders)])
        break
    break

# 크롬창 닫기
driver.close()

# 워크북(엑셀파일)을 원하는 이름으로 저장
f.save('RecipeCrawling.csv')