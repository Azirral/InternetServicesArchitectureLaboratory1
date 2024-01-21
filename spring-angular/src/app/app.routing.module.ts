import {RouterModule, Routes} from '@angular/router';
import {NgModule} from "@angular/core";
import {CustomerListComponent} from "./customer/view/customer-list/customer-list.component";
import {CustomerViewComponent} from "./customer/view/customer-view/customer-view.component";
import {CustomerEditComponent} from "./customer/view/customer-edit/customer-edit.component";
import {CustomerAddComponent} from "./customer/view/customer-add/customer-add.component";
import {OrderViewComponent} from "./order/view/order-view/order-view.component";
import {OrderEditComponent} from "./order/view/order-edit/order-edit.component";
import {OrderAddComponent} from "./order/view/order-add/order-add.component";
import {OrderListComponent} from "./order/view/order-list/order-list.component";

const routes: Routes = [
    {
        component:OrderAddComponent,
        path: "orders/add"
    },
    {
        component: CustomerAddComponent,
        path: "customers/add"
    },
    {
        component: CustomerListComponent,
        path: "customers"
    },
    {
        component: CustomerViewComponent,
        path: "customers/:uuid"
    },
    {
        component: CustomerEditComponent,
        path: "customers/:uuid/edit"
    },
    {
        component: OrderListComponent,
        path: "orders"
    },
    {
        component: OrderViewComponent,
        path: "orders/:uuid"
    },
    {
        component:OrderEditComponent,
        path: "orders/:uuid/edit"
    }
];

@NgModule({
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class AppRoutingModule {

}
