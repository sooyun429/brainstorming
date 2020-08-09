<template>
  <div>
    <h1 class="display-2 font-weight-bold mb-3">
      로그인
    </h1>
    <Login />
    <router-view />
  </div>
</template>

<script>
import Login from "@/components/User/Login.vue";
export default {
  name: "Login",

  components: {
    Login
  }
};
</script>

<style></style>
