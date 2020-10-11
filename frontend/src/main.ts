import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Amplify from 'aws-amplify'
import '@aws-amplify/ui-vue'
import awsExports from './aws-exports'

Amplify.configure(awsExports);

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
