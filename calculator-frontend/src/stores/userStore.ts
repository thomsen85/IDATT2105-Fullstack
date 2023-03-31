import { defineStore } from "pinia";
import axios from "axios";
import Cookies from "js-cookie";

const cookiesStorage: Storage = {
  setItem(key, state) {
    console.log("set item", key, state);
    return Cookies.set(key, state, { expires: 3 });
  },
  getItem(key) {
    const store = Cookies.get(key);
    if (store === undefined) {
      return "";
    }
    return Cookies.get(key) || "";
  },
  length: 0,
  clear: function (): void {
    Cookies.remove("user");
  },
  key: function (): string | null {
    throw new Error("Function not implemented.");
  },
  removeItem: function (): void {
    throw new Error("Function not implemented.");
  },
};

export const useUserStore = defineStore("user", {
  state: () => ({
    username: "",
    token: "",
  }),
  getters: {
    isAuthenticated: (state) => {
      return state.token !== "";
    },
  },
  actions: {
    async getTokenAndSaveInStore(username: string, password: string) {
      try {
        const response = await getJwtToken(username, password);
        const data = response.data;

        if (data != null && data != "" && data != undefined) {
          this.token = data;
          this.username = username;
          return true;
        }
        return false;
      } catch (err) {
        console.log(err);
        return false;
      }
    },
  },
  persist: {
    enabled: true,
    strategies: [{ key: "user", storage: cookiesStorage }],
  },
});

function getJwtToken(username: string, password: string) {
  return axios.post("http://localhost:8080/api/v1/public/token", {
    username,
    password,
  });
}
