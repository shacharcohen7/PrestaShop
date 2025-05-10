/* @provengo summon selenium */

bthread('buy product', function () {
  let s = new SeleniumSession('user_session');
  s.start(URL)
  userLogin(s)
  addProductToCart(s)
  proceedToCheckout(s)
  checkout(s)
  placeOrder(s)
});

bthread('delete product', function () {
  let s = new SeleniumSession('admin_session');
  s.start(ADMIN_URL);
  adminLogin(s)
  searchProduct(s)
  deleteProduct(s)
});

bthread('correction', function () {
  sync({
    waitFor: any('End(placeOrder)', {EndEvent: true}),
    block: any('Start(deleteProduct)', {StartEvent: true})
  })
})