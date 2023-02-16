// https://docs.cypress.io/api/introduction/api.html

describe("Contact test", () => {
  it("visits the contact root url", () => {
    cy.visit("/contact");
  });
  it("test contact", () => {
    cy.visit("/contact");
    cy.get("#name").type("Name");
    cy.get("#email").type("email@example.com");
    cy.get("#message").type("Message");
    cy.get("#submit-form").click();
    cy.get("#success").should("have.text", "true");
  });
});
