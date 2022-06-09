<template>
        <!--<i>{{image.id}}</i>-->
        <v-flex xs3>
                <v-card flat tile>
                        <v-img
                                :src="image.url"
                        >
                        </v-img>
                </v-card>

        <!--<i v-if="image.creationDate != null">Дата создания: {{image.creationDate}}</i>
        <i v-else>Дата создания: Отсутствует :(</i>-->
        </v-flex>
</template>

<script>
    export default {
        props: ['image']
    }
</script>

<style>
img.Image {
    width: 100%;
    max-width: 250px;
    height: auto;
    }
</style>