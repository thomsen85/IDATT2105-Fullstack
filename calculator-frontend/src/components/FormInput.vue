<template>
  <label :for="id">{{ label }}</label>
  <input
    @input="
      emit('update:modelValue', ($event.target as HTMLInputElement).value)
    "
    :type="type || 'text'"
  />
</template>

<script setup lang="ts">
import { toRefs } from "vue";

const props = defineProps<{
  id: string;
  label: string;
  modelValue: string;
  type?: string;
}>();

const { id, label, type } = toRefs(props);

const emit = defineEmits(["update:modelValue"]);
</script>

<style scoped>
label {
  display: block;
}

input {
  display: block;
  width: 10em;
  height: 2em;
}

</style>