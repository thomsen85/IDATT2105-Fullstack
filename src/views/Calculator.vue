<script setup lang="ts">
import { Ref, ref } from "vue";

const log: Ref<string[]> = ref([]);
const buffer = ref(0);
const current = ref(0);
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
const operation = ref("");
let comma = false;
let commaAmount = 1;

let action = (str: string) => {
  shuffle(elements.value);
  if (str === "C") {
    buffer.value = 0;
    current.value = 0;
    operation.value = "";
    comma = false;
  } else if (["*", "/", "-", "+", "%"].includes(str)) {
    if (operation.value === "") {
      operation.value = str;
      buffer.value = current.value;
      current.value = 0;
      comma = false;
      commaAmount = 1;
    }
  } else if (str === "=") {
    if (operation.value === "+") {
      if (buffer.value === 9 && current.value === 10) {
        log.value.push(`9 + 10 = 21`);
        current.value = 21;
      } else {
        log.value.push(
          `${buffer.value} + ${current.value} = ${buffer.value + current.value}`
        );
        current.value = buffer.value + current.value;
      }
    } else if (operation.value === "-") {
      log.value.push(
        `${buffer.value} - ${current.value} = ${buffer.value - current.value}`
      );
      current.value = buffer.value - current.value;
    } else if (operation.value === "*") {
      log.value.push(
        `${buffer.value} * ${current.value} = ${buffer.value * current.value}`
      );
      current.value = buffer.value * current.value;
    } else if (operation.value === "/") {
      log.value.push(
        `${buffer.value} / ${current.value} = ${buffer.value / current.value}`
      );
      current.value = buffer.value / current.value;
    } else if (operation.value === "%") {
      log.value.push(
        `${buffer.value} % ${current.value} = ${buffer.value % current.value}`
      );
      current.value = buffer.value % current.value;
    }
    operation.value = "";
    buffer.value = 0;
    comma = false;
    commaAmount = 1;
  } else if (str === ".") {
    comma = true;
  } else {
    if (comma) {
      console.log(current.value, "+", parseInt(str), "/", 10 * commaAmount);
      current.value = current.value + parseInt(str) / Math.pow(10, commaAmount);
      commaAmount += 1;
    } else {
      current.value = current.value * 10 + parseInt(str);
    }
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
    <p id="result">{{ current }}</p>
    <div class="buttons">
      <button
        v-for="n in elements"
        :key="n"
        @click="action(n)"
        v-bind:style="
          n === operation ? 'background-color: var(--secondary-color);' : ''
        "
      >
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
