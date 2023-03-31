<template>
  <form @submit.prevent="submit">
    <FormInput v-model="username" id="username" label="Username" />
    <FormInput
      v-model="password"
      id="password"
      label="Password"
      type="password"
    />
    <button type="submit">Login</button>
  </form>
</template>

<script setup lang="ts">
import { useUserStore } from "@/stores/userStore";
import { ref, watch } from "vue";
import { useRouter } from "vue-router";
import FormInput from "@/components/FormInput.vue";

const userStore = useUserStore();
const router = useRouter();

const username = ref("");
const password = ref("");


const submit = async () => {
  console.log("#########", username.value, password.value);
  if (await userStore.getTokenAndSaveInStore(username.value, password.value)) {
    router.push("/");
  }
};
</script>

<style></style>
