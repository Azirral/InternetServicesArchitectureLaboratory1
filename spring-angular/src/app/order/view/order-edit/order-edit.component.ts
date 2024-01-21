import {Component, OnInit} from '@angular/core';
import {OrderForm} from "../../model/order-form";
import {OrderService} from "../../service/order.service";
import {CustomerService} from "../../../customer/service/customer.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Customer} from "../../../customer/model/customer";
import {Customers} from "../../../customer/model/customers";

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrl: './order-edit.component.css'
})
export class OrderEditComponent implements OnInit{
  uuid: string | undefined;
  order: OrderForm | undefined;
  original: OrderForm | undefined;

  customers: Customers | undefined;

  constructor(
      private orderService: OrderService,
      private customerService: CustomerService,
      private router: Router,
      private route: ActivatedRoute
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.customerService.getCustomers()
          .subscribe(customers => this.customers = customers);

      this.orderService.getOrder(params['uuid'])
          .subscribe(order => {
            this.uuid = order.id;
            this.order = {
              date: order.date,
              amount:order.amount,
              customer_id: order.customer.id
            };
            this.original = {...this.order};
          });
    });
  }


  onSubmit(): void {
    this.orderService.putOrder(this.uuid!, this.order!)
        .subscribe(() => {this.router.navigate(['/orders', this.uuid])});
  }
}
