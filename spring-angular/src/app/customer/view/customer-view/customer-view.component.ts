import {Component, OnInit} from '@angular/core';
import {CustomerDetails} from "../../model/customer-details";
import {CustomerService} from "../../service/customer.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrl: './customer-view.component.css'
})
export class CustomerViewComponent implements OnInit{
  customer: CustomerDetails | undefined;

  constructor(private service: CustomerService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getCustomer(params['uuid'])
          .subscribe(customer => {
            this.customer = customer
          })
    });
  }

  onDelete(customer: CustomerDetails) {
    this.service.deleteCustomer(customer.id).subscribe(() => {
      this.router.navigate(['/customers'])
    })
  }
}
