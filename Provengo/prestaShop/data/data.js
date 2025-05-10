/*
 *  This is a good place to put common test data, project-wide constants, etc.
 */

const URL = 'http://localhost/stradivarious/';
const ADMIN_URL = 'http://localhost/stradivarious/admin4150un9qxx4ji25cqkv/'

const xpaths = {
  userLogin: {
    emailInput: "//*[@id=\"field-email\"]",
    passwordInput: "//*[@id=\"field-password\"]",
    submitLoginButton: "//*[@id=\"submit-login\"]"
  },
  adminLogin: {
    emailInput: "//input[@name=\"email\"]",
    passwordInput: "//input[@name=\"passwd\"]",
    submitLoginButton: "//*[@id='submit_login']"
  },
  home: {
    goToLoginButton: "/html/body/main/header/nav/div/div/div[1]/div[2]/div[2]/div/a",
    searchField: "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/form/input[2]",
  },
  searchResults: {
    firstProduct: "/html/body/main/section/div/div/div/section/section/div[3]/div[1]/div"
  },
  product: {
    addToCartButton: "/html/body/main/section/div/div/div/section/div[1]/div[2]/div[2]/div[2]/form/div[2]/div/div[2]/button"
  },
  cartPopUp: {
    proceedToCheckoutButton: "//*[@id='blockcart-modal']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]"
  },
  cart: {
    proceedToCheckoutButton: "//body/main[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]",
  },
  addresses: {
      continueButton: "//button[@name=\"confirm-addresses\"]"
  },
  shippingMethod: {
    continueButton: "//button[@name=\"confirmDeliveryOption\"]"
  },
  payment: {
    payByCashButton: "//*[@id=\"payment-option-1\"]",
    confirmTerms: "//*[@id='conditions-to-approve']/ul[1]/li[1]/div[1]/span[1]/input[1]",
    placeOrderButton: "//*[@id='payment-confirmation']/div[1]/button[1]"
  },
  orderConfirmation: {
    successMessage: "//*[@id='content-hook_order_confirmation']/div[1]/div[1]/div[1]/h3"
  },
  adminOperations: {
    catalogTab: "//html/body/nav/div/ul/li[6]/a",
    productsTab: "//*[@id='subtab-AdminProducts']/a[1]",
    searchProductByName: "//*[@id='product_name']",
    productOptions: "/html/body/div[2]/div/div[3]/div[2]/div/form[2]/table/tbody/tr/td[11]/div/div/a[2]",
    delete: "//*[contains(@data-title, \"Delete selection\")]",
    confirm: "//button[contains(@class, 'btn-confirm-submit')]"
  }
}

const userDetails = {
  user: {
    email: 'john@gmail.com',
    password: 'JohnLevi456'
  },
  admin: {
    email: 'shachco@post.bgu.ac.il',
    password: 'Shachar123?!'
  }  
};