<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertMarque = reactive({
        idMarque:route.query.idMarque,
        
    });

    const responseInsertMarque = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/marque/insertMarque';
            const response = await axios.post(url, objectInsertMarque);
            Object.assign(responseInsertMarque, response);
            router.push("/allMarque");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la marque" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertMarque.nomMarque" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
