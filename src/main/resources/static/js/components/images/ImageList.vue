<template>
    <v-layout align-center justify-start column>
        <generateForm :images="images" />
        <h2 class="font-weight-thin display-1 mt-5 pb-3">Галлерея</h2>

        <v-container grid-list-md fluid blue-grey lighten-5>
            <v-layout row wrap>
                <imageRow v-for="image in images"
                                  :key="image.id"
                                  :image="image"
                        />
            </v-layout>
        </v-container>
    </v-layout>
</template>


<script>
    import ImageRow from 'components/images/ImageRow.vue'
    import generateForm from 'components/images/ImageForm.vue'

    export default {
        props: ['images'],
        components: {
            ImageRow,
            generateForm
        },
    }
</script>


<style>

</style>