<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertTypeDisque = reactive({
        idTypeDisque:route.query.idTypeDisque,
        
    });

    const responseInsertTypeDisque = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/typeDisque/insertTypeDisque';
            const response = await axios.post(url, objectInsertTypeDisque);
            Object.assign(responseInsertTypeDisque, response);
            router.push("/allTypeDisque");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la typeDisque" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertTypeDisque.nomTypeDisque" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
