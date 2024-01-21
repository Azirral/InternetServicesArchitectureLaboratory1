import {Component, OnInit} from '@angular/core';

import {Customer} from "../../model/customer";
import {CustomerService} from "../../service/customer.service";
import {Customers} from "../../model/customers";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit{

  customers: Customers | undefined;

  /**
   * @param service customers service
   */
  constructor(private service: CustomerService) {
  }


  ngOnInit(): void {
    this.service.getCustomers().subscribe(customers=> this.customers = customers);
  }

  onDelete(customer: Customer): void {
    this.service.deleteCustomer(customer.id).subscribe(() => this.ngOnInit());
  }
}
