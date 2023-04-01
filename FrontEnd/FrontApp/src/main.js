
import {compile, createApp} from 'vue';
import App from './App.vue';
import PrimeVue from 'primevue/config';

import "primevue/resources/themes/md-light-indigo/theme.css";
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import '/node_modules/primeflex/primeflex.css'



import DataTable from 'primevue/datatable';
import Column from 'primevue/column';
import ColumnGroup from 'primevue/columngroup';     
import Row from 'primevue/row';
import Button from 'primevue/button'; 
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import InputMask from 'primevue/inputmask';


const app = createApp(App);

app.component('DataTable', DataTable)
app.component('Column', Column)
app.component('ColumnGroup', ColumnGroup)
app.component('Row', Row)
app.component('Button', Button)
app.component('Dialog', Dialog)
app.component('InputMask', InputMask)
app.component('InputText', InputText)

app.use(PrimeVue);
app.mount('#app');






