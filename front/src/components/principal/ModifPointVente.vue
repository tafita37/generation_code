<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertPointVente = reactive({
        idPointVente:route.query.idPointVente,
        
    });

    const responseInsertPointVente = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/pointVente/insertPointVente';
            const response = await axios.post(url, objectInsertPointVente);
            Object.assign(responseInsertPointVente, response);
            router.push("/allPointVente");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la pointVente" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertPointVente.nomPointVente" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
