import { describe, it, expect, beforeEach } from "vitest";
import { createPinia, setActivePinia } from "pinia";

import { mount } from "@vue/test-utils";
import ContactForm from "../forms/ContactForm.vue";

describe("ContactForm", () => {
  beforeEach(() => {
    // creates a fresh pinia and make it active so it's automatically picked
    // up by any useStore() call without having to pass it to it:
    // `useStore(pinia)`
    setActivePinia(createPinia());
  });

  it("renders properly", () => {
    const wrapper = mount(ContactForm, {});
    expect(wrapper.text()).toContain("Contact Form");
  });
});
