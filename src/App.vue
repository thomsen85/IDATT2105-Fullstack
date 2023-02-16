<script setup lang="ts"></script>

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
      </ul>
    </nav>
  </header>

  <div class="site-content">
    <router-view></router-view>
  </div>
</template>

<style scoped>
.logo {
  text-decoration: none;
  color: var(--text-color);
}
</style>
