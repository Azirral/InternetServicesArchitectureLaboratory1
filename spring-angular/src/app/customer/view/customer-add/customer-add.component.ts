import {Component, OnInit} from '@angular/core';
import {CustomerForm} from "../../model/customer-form";
import {CustomerService} from "../../service/customer.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrl: './customer-add.component.css'
})
export class CustomerAddComponent implements OnInit{
  customer: CustomerForm = {
    name: '',
    email: ''
  };

  constructor(
      private customerService: CustomerService,
      private router: Router
  ) {
  }

  ngOnInit() {
  }

  onSubmit(): void {
    this.customerService.postCustomer(this.customer)
        .subscribe(() => this.router.navigate(['/customers']));
  }

}
