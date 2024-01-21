import {Customer} from "../../customer/model/customer";

export interface Order {
    id: string;
    date: string;
    customer: Customer;
}
