<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertDisque = reactive({
        idDisque:route.query.idDisque,
        idTypeDisque : {},

    });

    const responseInsertDisque = reactive({});
    
            const allIdTypeDisque=reactive({});


    onMounted(() => {
                const getAllIdTypeDisque = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/typeDisque/allTypeDisque';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdTypeDisque, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdTypeDisque();
 
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/disque/insertDisque';
            const response = await axios.post(url, objectInsertDisque);
            Object.assign(responseInsertDisque, response);
            router.push("/allDisque");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la disque" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertDisque.capacite" type="number">
<input v-model="objectInsertDisque.vitesse" type="number">
<select  v-model="objectInsertDisque.idTypeDisque.idTypeDisque" id="">
    <option v-for="(idTypeDisque) in allIdTypeDisque" :key="idTypeDisque.idTypeDisque" :value="idTypeDisque.idTypeDisque">
        {{ idTypeDisque.label }}
    </option>
</select>

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
