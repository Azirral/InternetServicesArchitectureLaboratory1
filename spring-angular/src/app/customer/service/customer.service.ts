import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, mergeMap, Observable} from "rxjs";
import {CustomerDetails} from "../model/customer-details";
import {Customers} from "../model/customers";
import {CustomerForm} from "../model/customer-form";
import {Orders} from "../../order/model/orders";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) {

  }

  //get all customers
  getCustomers(): Observable<Customers> {
    return this.http.get<Customers>('/api/customers');
  }

  //get a customer
  getCustomer(uuid: string): Observable<CustomerDetails> {
    return this.http.get<CustomerDetails>('/api/customers/' + uuid).pipe(
        mergeMap((customer: CustomerDetails) => {
          return this.http.get<Orders>('api/orders/', {params: {customer_id: uuid}}).pipe(
              map((orders: Orders) => {
                customer.orders = orders;
                return customer;
              })
        );
        })
    );
  }

  //delete customer
  deleteCustomer(uuid: string): Observable<any> {
    return this.http.delete('/api/customers/' + uuid);
  }

  //create customer
  postCustomer(request: CustomerForm): Observable<any> {
    return this.http.post('/api/customers', request);
  }

  //update customer
  putCustomer(uuid: string, request: CustomerForm): Observable<any>{
    return this.http.put('/api/customers/' + uuid, request);
  }

}
