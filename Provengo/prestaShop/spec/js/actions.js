/* @provengo summon selenium */
/* @provengo summon textassertion */

function userLogin(session){
  with(session){
    click(xpaths.home.goToLoginButton)
    writeText(xpaths.userLogin.emailInput, userDetails.user.email)
    writeText(xpaths.userLogin.passwordInput, userDetails.user.password)
    click(xpaths.userLogin.submitLoginButton)
  }
}

function addProductToCart(session){
  with(session){
    writeText(xpaths.home.searchField, "T-Shirt"+"\uE006")
    click(xpaths.searchResults.firstProduct)
    click(xpaths.product.addToCartButton)
  }
}

function proceedToCheckout(session){
  with(session){
    click(xpaths.cartPopUp.proceedToCheckoutButton)
    click(xpaths.cart.proceedToCheckoutButton)
  }
}

function checkout(session){
  with(session){
    click(xpaths.addresses.continueButton)
    click(xpaths.shippingMethod.continueButton)
    click(xpaths.payment.payByCashButton)
    click(xpaths.payment.confirmTerms)
  }
}

function placeOrder(session){
  sync({ request: Event('Start(placeOrder)', {StartEvent: true}) });
  with(session){
    click(xpaths.payment.placeOrderButton)
    assertText(xpaths.orderConfirmation.successMessage, "YOUR ORDER IS CONFIRMED", TextAssertions.modifiers.Contains);
  }
  sync({ request: Event('End(placeOrder)', {EndEvent: true}) });
}

function adminLogin(session){
  with(session){
    writeText(xpaths.adminLogin.emailInput, userDetails.admin.email)
    writeText(xpaths.adminLogin.passwordInput, userDetails.admin.password)
    click(xpaths.adminLogin.submitLoginButton)
  }
}

function searchProduct(session){
  with(session){
    click(xpaths.adminOperations.catalogTab)
    click(xpaths.adminOperations.productsTab)
    writeText(xpaths.adminOperations.searchProductByName, "T-Shirt"+"\uE006")
  }
}

function deleteProduct(session){
  sync({ request: Event('Start(deleteProduct)', {StartEvent: true}) });
  with(session){
    click(xpaths.adminOperations.productOptions)
    click(xpaths.adminOperations.delete)
    click(xpaths.adminOperations.confirm)
  }
  sync({ request: Event('End(deleteProduct)', {EndEvent: true}) });
}