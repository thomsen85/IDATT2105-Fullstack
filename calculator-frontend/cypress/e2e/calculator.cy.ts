// https://docs.cypress.io/api/introduction/api.html

describe("Calculator test", () => {
  it("visits the calculator root url", () => {
    cy.visit("/calculator");
    cy.contains("h2", "Calculator");
  });
  it("test calculator", () => {
    cy.visit("/calculator");
    cy.get("button").contains("5").click();
    cy.get("button").contains("+").click();
    cy.get("button").contains("7").click();
    cy.get("button").contains("=").click();
    cy.get("#result").should("have.text", "12");
  });
});
