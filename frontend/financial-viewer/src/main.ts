import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Amplify from 'aws-amplify'
import '@aws-amplify/ui-vue'

Amplify.configure({
  Auth: {
    // REQUIRED only for Federated Authentication - Amazon Cognito Identity Pool ID
    identityPoolId: 'ap-northeast-1_VKzuy25rR',

    // REQUIRED - Amazon Cognito Region
    region: 'ap-northeast-1',

    // OPTIONAL - Amazon Cognito User Pool ID
    userPoolId: 'ap-northeast-1_VKzuy25rR',

    // OPTIONAL - Amazon Cognito Web Client ID (26-char alphanumeric string)
    userPoolWebClientId: '2p99jiiagij7jjnfe0ifkhn11d'
  }
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
