<template>
  <v-app>
    <v-app-bar app color="#4F7349" dark>
      <div class="d-flex align-center"></div>

      <v-spacer></v-spacer>

      <v-btn href="https://github.com/vuetifyjs/vuetify/releases/latest" target="_blank" text>
        회원가입
      </v-btn>
    </v-app-bar>

    <v-content>
      <router-view />
    </v-content>
    <!-- <v-main>
      <router-view />
    </v-main> -->
  </v-app>
</template>

<script>
export default {
  name: "App",
  data: () => ({})
};
</script>
