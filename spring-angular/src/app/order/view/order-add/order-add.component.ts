import {Component, OnInit} from '@angular/core';
import {OrderForm} from "../../model/order-form";
import {OrderService} from "../../service/order.service";
import {CustomerService} from "../../../customer/service/customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customers} from "../../../customer/model/customers";

@Component({
  selector: 'app-order-add',
  templateUrl: './order-add.component.html',
  styleUrl: './order-add.component.css'
})
export class OrderAddComponent implements OnInit{

  order: OrderForm = {
    date: '',
    amount: 0,
    customer_id: ''
  }

  customers: Customers | undefined;

  constructor(
      private orderService: OrderService,
      private customerService: CustomerService,
      private router: Router,
  ) {
  }

  ngOnInit() {
    this.customerService.getCustomers().subscribe(customers => this.customers = customers);
  }

  onSubmitOrder(): void {
    this.orderService.postOrder(this.order)
        .subscribe(() => this.router.navigate(['/orders']));
  }

}
