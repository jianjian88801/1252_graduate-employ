import Vue from 'vue'
import App from './App.vue'
import router, {resetRouter} from "./router";
import store from "./store";
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import {userRouterMap} from '@/router/user_router'
import {companyRouterMap} from '@/router/company_router'
import {studentRouterMap} from '@/router/student_router'
import local from "./store/local";

import './assets/css/admin.css'

Vue.use(ElementUI);
Vue.config.productionTip = false
// Vue.prototype.$message = message

//v-title
Vue.directive('title', {
    update: function (el) {
        document.title = el.dataset.title;
    }
})
if(local.getToken()){
    resetRouter()
    if(local.getUserInfo().type == 0){//管理员
        router.addRoutes(userRouterMap)
    }
    if(local.getUserInfo().type == 1){//企业
        router.addRoutes(companyRouterMap)
    }
    if(local.getUserInfo().type == 2){//学生
        router.addRoutes(studentRouterMap)
    }
}

new Vue({
    router,
    store,
    render: h => h(App),
}).$mount('#app')