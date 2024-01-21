import {Component, OnInit} from '@angular/core';
import {OrderDetails} from "../../model/order-details";
import {Customer} from "../../../customer/model/customer";
import {OrderService} from "../../service/order.service";
import {CustomerService} from "../../../customer/service/customer.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-view',
  templateUrl: './order-view.component.html',
  styleUrl: './order-view.component.css'
})
export class OrderViewComponent implements OnInit{
  order: OrderDetails | undefined;

  constructor(
      private orderService: OrderService,
      private router: Router,
      private route: ActivatedRoute
  ) {
  }
  ngOnInit() {
    this.route.params.subscribe(params => {
      this.orderService.getOrder(params['uuid'])
          .subscribe(order => this.order = order)
    });
  }

  onDelete(order: OrderDetails) {
    this.orderService.deleteOrder(order.id).subscribe(()=>{
      this.router.navigate(['/orders']);
    })
  }
}
