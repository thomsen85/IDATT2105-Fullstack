<script setup lang="ts">
import { useUserStore } from "@/stores/userStore";
import axios from "axios";
import { onMounted, ref } from "vue";
import type { Ref } from "vue";

const log: Ref<string[]> = ref([]);
const expression = ref("");
const elements = ref([
  "C",
  "*",
  "/",
  "-",
  "1",
  "2",
  "3",
  "+",
  "4",
  "5",
  "6",
  "%",
  "7",
  "8",
  "9",
  "=",
  "0",
  ".",
]);

const userStore = useUserStore();

onMounted(() => {
  type Calculation = {
    answer: String;
    calculation: String;
  };
  axios
    .get("http://localhost:8080/api/v1/private/calculation", {
      headers: {
        Authorization: "Bearer " + userStore.token,
      },
    })
    .then((res) => {
      console.log(res);
      res.data.forEach((element: Calculation) => {
        console.log(element);
        log.value.push(element.calculation + " = " + element.answer);
      });
    });
});

let action = (str: string) => {
  shuffle(elements.value);
  if (str === "C") {
    expression.value = "";
  } else if (str === "=") {
    console.log("expression: " + expression.value);
    axios
      .post(
        "http://localhost:8080/api/v1/private/calculation",
        {
          calculation: expression.value,
        },
        {
          headers: {
            Authorization: "Bearer " + userStore.token,
          },
        }
      )
      .then((res) => {
        console.log(res);
        log.value.push(expression.value + " = " + res.data.answer);
        expression.value = res.data.answer;
      });
  } else {
    expression.value += str;
  }
};

function shuffle(array: any[]) {
  let currentIndex = array.length,
    randomIndex;

  // While there remain elements to shuffle.
  while (currentIndex != 0) {
    // Pick a remaining element.
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex--;

    // And swap it with the current element.
    [array[currentIndex], array[randomIndex]] = [
      array[randomIndex],
      array[currentIndex],
    ];
  }

  return array;
}
</script>

<template>
  <div class="calculator">
    <h2 class="calculator-title">Calculator</h2>
    <p id="result">{{ expression }}</p>
    <div class="buttons">
      <button v-for="n in elements" :key="n" @click="action(n)">
        {{ n }}
      </button>
    </div>
    <div class="">
      <h3 class="log-title">Log</h3>
      <ul>
        <li v-for="l in log" :key="l">{{ l }}</li>
      </ul>
    </div>
  </div>
</template>

<style scoped>
* {
  font-size: 1.5rem;
}

input {
  text-align: center;
}
.calculator {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
}

.buttons {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
}
</style>
