import Vue from 'vue'
import VueResource from 'vue-resource'
import App from 'pages/App.vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'


Vue.use(VueResource)
Vue.use(Vuetify)

new Vue({
    el: '#app',
    render: a => a(App)

})

/*
var imageApi = Vue.resource('/images{/id}');


Vue.component('generateForm', {
    props: ['images'],

    methods: {
        generate: function() {
            imageApi.save({},).then(result =>
                result.json().then(data => {
                    this.images.unshift(data);
                })
            )
        }
    }

});

Vue.component('image-row', {
props: ,
template:


});

Vue.component('GalleryList', {

    template:

});

*/
