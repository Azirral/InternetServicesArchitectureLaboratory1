import {NgModule} from "@angular/core";
import {AppComponent} from "./app.component";
import {OrderService} from "./order/service/order.service";
import {CustomerService} from "./customer/service/customer.service";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {AppRoutingModule} from "./app.routing.module";
import {BrowserModule} from "@angular/platform-browser";
import {FooterComponent} from "./components/footer/footer.component";
import {HeaderComponent} from "./components/header/header.component";
import {NavComponent} from "./components/nav/nav.component";
import {MainComponent} from "./components/main/main.component";
import {CustomerListComponent} from "./customer/view/customer-list/customer-list.component";
import {CustomerEditComponent} from "./customer/view/customer-edit/customer-edit.component";
import {CustomerViewComponent} from "./customer/view/customer-view/customer-view.component";
import {CustomerAddComponent} from "./customer/view/customer-add/customer-add.component";
import {OrderListComponent} from "./order/view/order-list/order-list.component";
import {OrderViewComponent} from "./order/view/order-view/order-view.component";
import {OrderEditComponent} from "./order/view/order-edit/order-edit.component";
import {OrderAddComponent} from "./order/view/order-add/order-add.component";
import {ErrorInterceptor} from "../error.interceptor";

@NgModule({
    declarations: [
      AppComponent,
      FooterComponent,
      HeaderComponent,
      NavComponent,
      MainComponent,
      CustomerListComponent,
      CustomerEditComponent,
      CustomerViewComponent,
      CustomerAddComponent,
      OrderListComponent,
      OrderViewComponent,
      OrderEditComponent,
      OrderAddComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule
    ],
    providers: [
        CustomerService,
        OrderService,
      {
        provide: HTTP_INTERCEPTORS,
        useClass: ErrorInterceptor,
        multi: true
      }
    ],
    bootstrap: [
        AppComponent
    ]
})
export class AppModule {

}
