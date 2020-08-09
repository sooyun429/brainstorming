<template>
  <div>
    <h1 class="display-2 font-weight-bold mb-3">
      회원가입
    </h1>
    <Signup />
  </div>
</template>

<script>
import Signup from "@/components/User/Signup.vue";
export default {
  name: "Signup",

  components: {
    Signup
  }
};
</script>

<style></style>
