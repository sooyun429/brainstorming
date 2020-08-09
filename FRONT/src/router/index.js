import Vue from "vue";
import VueRouter from "vue-router";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    component: () => import("@/components/Main.vue"),
    children: [
      // { path: "login", components: () => import("@/views/LoginPage.vue") }
      { path: "login", components: () => import("@/components/User/Login.vue") }
    ]
  }
  // {
  //   path: "/signup",
  //   name: "Signup",
  //   component: () => import("@/views/SignupPage.vue")
  // },
  // {
  //   path: "/login",
  //   name: "Login",
  //   component: () => import("@/components/User/Login.vue")
  //   // component: () => import("@/views/LoginPage.vue")
  // }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
