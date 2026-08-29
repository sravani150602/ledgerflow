import http from 'k6/http';
import { check, sleep } from 'k6';
export const options={vus:300,duration:'30s',thresholds:{http_req_duration:['p(95)<150'],http_req_failed:['rate<0.01']}};
const base=__ENV.BASE_URL||'http://localhost:8080'; const product=__ENV.PRODUCT_ID; const customer=__ENV.CUSTOMER_ID||'00000000-0000-0000-0000-000000000001';
export default function(){const r=http.post(`${base}/api/v1/orders`,JSON.stringify({customerId:customer,items:[{productId:product,quantity:1}]}),{headers:{'Content-Type':'application/json'}});check(r,{'accepted':x=>x.status===202});sleep(0.1);}
