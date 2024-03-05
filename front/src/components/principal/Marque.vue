<script setup>
    import { onMounted, reactive, getCurrentInstance  } from 'vue';
    import axios from 'axios';
    import TitleListe from '../reuse/TitleListe.vue';
    import ListeGenerale from '../reuse/ListeGenerale.vue';
    import H5TitleListe from '../reuse/H5TitleListe.vue';
    import SpanListe from '../reuse/SpanListe.vue';
    import ModifSupprButton from '../reuse/ModifSupprButton.vue';
    import router from '@/router';

    const allMarque=reactive({});

    const getAllMarque = async () => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/marque/allMarque';
            const response = await axios.get(urlApiSpringBoot);
            Object.assign(allMarque, response.data);
        } catch (error) {
            console.log(error.response);
        }
    };

    const loadFormModif = (idMarque) => {
        router.push({path : "/modifMarque", query: {idMarque : idMarque}});
    }

    const deleteMarqueById = (idMarque) => {
        try {
            const urlApiSpringBoot = 'http://localhost:8080/marque/deleteMarqueById?idMarque='+idMarque;
            const response = axios.get(urlApiSpringBoot);
            getCurrentInstance().proxy.forceUpdate();
        } catch (error) {
            console.error('Erreur lors de l\'appel de l\'API', error);
        }
    }

    onMounted(getAllMarque);
</script>
<template>
    <TitleListe title-liste="Liste des marque"/>
    <ListeGenerale>
        <div class="child_computer" v-for="(marque)  in allMarque" :key="marque.idMarque">
            <H5TitleListe labels="" :title-h5="marque.idMarque"/>
            <SpanListe :title-span='marque.idMarque' labels=''/><SpanListe :title-span='marque.nomMarque' labels=''/>
            <ModifSupprButton :funct-modif="() => loadFormModif(marque.idMarque)" :funct-delete="() => deleteMarqueById(marque.idMarque)"/>
        </div>
    </ListeGenerale>
</template>
<style scoped>
    @import "@/assets/css/liste.css";
</style>
