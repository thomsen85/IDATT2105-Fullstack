import { createRouter, createWebHistory } from "vue-router";
import { useUserStore } from "../stores/userStore";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/calculator",
      name: "calculator",
      component: () => import("../views/Calculator.vue"),
      meta: { requireAuth: true },
    },
    {
      path: "/contact",
      name: "contact",
      component: () => import("../views/ContactSite.vue"),
    },
    {
      path: "/login",
      name: "login",
      component: () => import("../views/LoginView.vue"),
    }
  ],
});

router.beforeEach((to, from, next) => {
  const requireAuth = to.matched.some((record) => record.meta.requireAuth);
  const userStore = useUserStore();

  const isAuthenticated = userStore.isAuthenticated;

  if (requireAuth && !isAuthenticated) {
    next("/login");
  } else {
    next();
  }
});

export default router;
