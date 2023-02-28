import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart/cart.service';
import { OrderService } from 'src/app/services/order/order.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  public product: any=[];
  public grandTotal:number =0;
  public productList : any;
  public orderList: any=[];
  public quantity: number = 1;
  public id : number = 1;
  paymentHandler: any = null;




  constructor(public cartService:CartService, public orderService: OrderService) { }

  ngOnInit(): void {
   this.cartService.getProducts()

    //this.orderService.getAllOrders()
    .subscribe(
      res =>{
        console.log(res);
        // when initializing an item with id:
        this.product = res;
        this.grandTotal = this.cartService.getTotal();
        this.invokeStripe();

      }
    );

  }

  makePayment(amount: any) {
    const paymentHandler = (<any>window).StripeCheckout.configure({
      key: 'pk_test_51KsMPsFviP3FCg2KC7JmkDFdiqgKmXcvXXyqBXctTDBPqYVaEKsftR2vkXhNqSy7yLKBmzigDRf9cQeJUtMnAS1600oRpHcrQp',
      locale: 'auto',
      token: function (stripeToken: any) {
        console.log(stripeToken);
        alert('Stripe token generated!');
      },
    });
    paymentHandler.open({
      name: 'Hotel Barrière',
      description: 'Paiement réservation',
      amount: amount * 100,
      image: "http://localhost:8089/images/logo1.png",
    });
  }

  invokeStripe() {
    if (!window.document.getElementById('stripe-script')) {
      const script = window.document.createElement('script');
      script.id = 'stripe-script';
      script.type = 'text/javascript';
      script.src = 'https://checkout.stripe.com/checkout.js';
      script.onload = () => {
        this.paymentHandler = (<any>window).StripeCheckout.configure({
          key: 'pk_test_51KsMPsFviP3FCg2KC7JmkDFdiqgKmXcvXXyqBXctTDBPqYVaEKsftR2vkXhNqSy7yLKBmzigDRf9cQeJUtMnAS1600oRpHcrQp',
          locale: 'auto',
          token: function (stripeToken: any) {
            console.log(stripeToken);
            // Create Order here
            alert('Le paiement a été réussi!');
          },
        });
      };
      window.document.body.appendChild(script);
    }
  }

  removeCartItem(item:any){
    this.cartService.removeCartItem(item);

  }
  emptyCart(){
    this.cartService.removeAllCart();
  }

  incProduct(item:any) {

      this.cartService.inc(item);

  }


  decProduct() {

      this.quantity --;

  }
}
