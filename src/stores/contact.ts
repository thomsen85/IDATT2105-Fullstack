import { ref, computed } from "vue";
import { defineStore } from "pinia";

export const useContactStore = defineStore("contact", () => {
  const email = ref('');
  const name = ref('');

  return { email, name };
});
