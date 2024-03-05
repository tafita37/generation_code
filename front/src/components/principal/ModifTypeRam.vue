<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertTypeRam = reactive({
        idTypeRam:route.query.idTypeRam,
        
    });

    const responseInsertTypeRam = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/typeRam/insertTypeRam';
            const response = await axios.post(url, objectInsertTypeRam);
            Object.assign(responseInsertTypeRam, response);
            router.push("/allTypeRam");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la typeRam" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertTypeRam.nomTypeRam" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
