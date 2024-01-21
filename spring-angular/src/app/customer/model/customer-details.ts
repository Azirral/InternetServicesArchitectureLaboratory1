import {Orders} from "../../order/model/orders";

export interface CustomerDetails{
    id: string;
    name: string;
    email: string;
    orders: Orders
}
