var imageApi = Vue.resource('/images{/id}');


Vue.component('generateForm', {
    props: ['images'],
    /*data: function() {
        return {

        }
    }*/

    template:
     '<div>' +
        '<h3>Генератор изображений</h3>' +
        '<input type="button" value = "Сгенерировать" @click="generate" />' +
    '</div>',

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
props: ['image'],
template: '<div><i>{{image.id}}</i><img :src="image.url" /></div>'


});

Vue.component('GalleryList', {
    props: ['images'],
    template:
    '<div>' +
        '<generateForm :images="images" />' +
        '<h3>Галлерея</h3>' +
        '<image-row v-for="image in images" :key="image.id" :image="image" />' +
    '</div>',
  created: function() {
    imageApi.get().then(result =>
        result.json().then(data =>
            data.forEach(image => this.images.push(image))

        )
    )
  }
});


var app = new Vue({
  el: '#app',
  template: '<GalleryList :images="images" />',
  data: {
    images: [

    ]
  }
});