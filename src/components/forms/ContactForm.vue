<script setup lang="ts">
import { Ref, ref } from "vue";
import { useContactStore } from "@/stores/contact.js";
import axios from "axios";

const errors: Ref<string[]> = ref([]);
const store = useContactStore();
const name = ref(store.$state.name);
const email = ref(store.$state.email);
const message = ref("");
const success = ref(false);
const response = ref("");
const activeClass = ref("active");

const submitForm = () => {
  errors.value = [];

  if (!name.value) {
    errors.value.push("Name required.");
  }

  if (!email.value) {
    errors.value.push("Email required.");
  }

  if (!message.value) {
    errors.value.push("Message required.");
  }

  if (!/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/.test(email.value)) {
    errors.value.push("Valid email required.");
  }

  if (errors.value.length) {
    return;
  }

  axios
    .post("//jsonplaceholder.typicode.com/posts", {
      name: name,
      email: email,
      message: message,
    })
    .then((r) => {
      success.value = true;
      response.value = JSON.stringify(r, null, 2);
    })
    .catch((error) => {
      response.value = "Error: " + error.response.status;
    });

  message.value = "";
  store.$patch({ email: email.value, name: name.value });
};
</script>

<template>
  <form @submit.prevent="submitForm">
    <h2>Contact Form</h2>
    <div v-if="errors?.length">
      <b>Please correct the following error(s):</b>
      <ul>
        <li v-for="error in errors" :key="error">{{ error }}</li>
      </ul>
    </div>

    <div>
      <label for="name">Name:</label><br />
      <input id="name" type="text" v-model="name" required />
    </div>

    <div>
      <label for="email">Email:</label><br />
      <input id="email" type="email" v-model="email" required />
    </div>

    <div>
      <label for="message">Message</label>
      <textarea id="message" name="message" v-model="message"></textarea>
    </div>

    <button :class="[name ? activeClass : '']" type="submit" id="submit-form">Submit</button>
    <div>
      <h3>Data retrieved from server:</h3>
      <p v-if="success" id="success">{{ success }}</p>
      <pre>{{ response }}</pre>
    </div>
  </form>
</template>

<style scoped>
form {
  width: 500px;
  padding: 10px 40px;
}

input,
textarea {
  border: 1px solid #ccc;
  color: #333;
  width: calc(100% - 30px);
}

input,
textarea,
button {
  border-radius: 4px;
  padding: 8px 15px;
}

div {
  margin: 20px 0;
}

pre-content {
  width: 500px;
}
</style>
