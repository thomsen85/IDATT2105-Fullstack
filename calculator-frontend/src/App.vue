<template>
  <header>
    <router-link class="logo" to="/">
      <h1>My site</h1>
    </router-link>
    <nav>
      <ul>
        <router-link class="button" to="/">Home</router-link>
        <router-link class="button" to="/calculator">Calculator</router-link>
        <router-link class="button" to="/contact">Contact</router-link>
        <router-link v-if="loggedIn" class="button" to="/logout">Logout</router-link>
        <router-link v-else class="button" to="/login">Login</router-link>
      </ul>
    </nav>
  </header>

  <div class="site-content">
    <router-view></router-view>
  </div>
</template>

<script setup lang="ts">
import { useUserStore } from "./stores/userStore";
import { computed } from "vue";

const userStore = useUserStore();
const loggedIn = computed(() => userStore.isAuthenticated);
</script>

<style scoped>
.logo {
  text-decoration: none;
  color: var(--text-color);
}
</style>
