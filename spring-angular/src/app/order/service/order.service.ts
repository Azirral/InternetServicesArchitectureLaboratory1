import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {map, Observable} from "rxjs";
import {OrderDetails} from "../model/order-details";
import {Orders} from "../model/orders";
import {OrderForm} from "../model/order-form";

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  constructor(private http:HttpClient) {
  }

  //get order
  getOrder(uuid: string): Observable<OrderDetails>{
    return this.http.get<OrderDetails>('/api/orders/' + uuid).pipe(
      map((order: OrderDetails) => {return order;})
    );
  }

  //get all orders
  getOrders(): Observable<Orders>{
    return this.http.get<Orders>('/api/orders');
  }

  //delete order
  deleteOrder(uuid: string):Observable<any>{
    return this.http.delete('/api/orders/' + uuid)
  }

  //update
  putOrder(uuid:string, request:OrderForm): Observable<any>{
    return this.http.put('/api/orders/' + uuid, request)
  }

  //create
  postOrder(request: OrderForm):Observable<any>{
    return this.http.post('/api/orders',request)
  }
}
