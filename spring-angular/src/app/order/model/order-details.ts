import {Customer} from "../../customer/model/customer";

export interface OrderDetails {
    id: string;
    date: string;
    amount: number;
    customer: Customer
}
