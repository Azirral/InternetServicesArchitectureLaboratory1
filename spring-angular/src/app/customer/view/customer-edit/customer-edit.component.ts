import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CustomerForm} from "../../model/customer-form";
import {CustomerService} from "../../service/customer.service";
import {Customers} from "../../model/customers";

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrl: './customer-edit.component.css'
})
export class CustomerEditComponent implements OnInit {

  uuid: string | undefined;

  customer: CustomerForm | undefined;

  original: CustomerForm | undefined;

  customers: Customers | undefined;

  constructor(
      private customerService: CustomerService,
      private route: ActivatedRoute,
      private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {

      this.customerService.getCustomer(params['uuid'])
          .subscribe(customer => {
            this.uuid = customer.id;
            this.customer = {
              name: customer.name,
              email: customer.email,
            };
            this.original = {...this.customer};
          });
    });
  }

  onSubmit(): void {
    this.customerService.putCustomer(this.uuid!, this.customer!)
        .subscribe(() => this.router.navigate(['/customers', this.uuid]));
  }
}
