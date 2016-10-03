import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import eu.fbk.dh.tint.runner.outputters.JSONOutputter;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by alessio on 05/07/16.
 */

public class Test {

    public static void main(String[] args) {

        String text = "La sonda Juno de la NASA, la nave impulsada por energía solar que ha viajado más lejos en el espacio, ha llegado este martes tras cinco años de viaje a la órbita de Júpiter, el planeta más grande del Sistema Solar, a la que dará 37 vueltas antes de estrellarse contra su superficie. El expresidente del Gobierno asegura que el secretario general del PSOE debería dimitir si recibe un revés del comité federal.";
        text = "El 50,2% decidió votar en contra del acuerdo de paz entre el Gobierno y las FARC, por el 49.7% que se decantó por el ‘sí’. La abstención, de más del 60%, y la pésima imagen de la guerrilla han sido determinantes en el resultado de la votación, que ninguna encuesta supo predecir. Evitar que continúe el conflicto armado, que ha atravesado el país durante más de 50 años y ha dejado ocho millones de víctimas, es el primer desafío. El presidente, Juan Manuel Santos, ha asegurado que el cese bilateral del fuego seguirá vigente. Colombia se adentra, no obstante, en un limbo plagado de incertidumbre. Nadie sabe con exactitud qué va a ocurrir a partir de ahora.";
        String posModel = "/Users/alessio/Documents/scripts/ixa-pipe-pos/src/main/resources/morph-models-1.5.0/es/es-pos-perceptron-autodict01-ancora-2.0.bin";
        String lemmaModel = "/Users/alessio/Documents/scripts/ixa-pipe-pos/src/main/resources/morph-models-1.5.0/es/es-lemma-perceptron-ancora-2.0.bin";

        Properties properties = new Properties();
        properties.setProperty("annotators", "spanish, readability");

        properties.setProperty("spanish.posModel", posModel);
        properties.setProperty("spanish.lemmaModel", lemmaModel);
        properties.setProperty("readability.language", "es");

        properties.setProperty("customAnnotatorClass.spanish", "eu.fbk.dh.spanish.BasicAnnotator");
        properties.setProperty("customAnnotatorClass.readability", "eu.fbk.dh.tint.readability.ReadabilityAnnotator");

        StanfordCoreNLP corenlp = new StanfordCoreNLP(properties);
        Annotation document = new Annotation(text);
        corenlp.annotate(document);

        try {
            String json = JSONOutputter.jsonPrint(document);
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

//        for (CoreLabel token : document.get(CoreAnnotations.TokensAnnotation.class)) {
//            System.out.println(token.word());
//        }

//        Readability readability = document.get(ReadabilityAnnotations.ReadabilityAnnotation.class);
//        System.out.println(readability);

//        try {
//            System.out.println(JSONOutputter.jsonPrint(document));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        try {
//            InputStream is = new ByteArrayInputStream(text.getBytes());
//            BufferedReader br = new BufferedReader(new InputStreamReader(is));
//
//            Properties tokProperties = new Properties();
//            tokProperties.setProperty("hardParagraph", "yes");
//            tokProperties.setProperty("language", "es");
//            tokProperties.setProperty("untokenizable", "yes");
//
//            final Properties posProperties = new Properties();
//            posProperties.setProperty("model",
//                    "/Users/alessio/Documents/scripts/ixa-pipe-pos/src/main/resources/morph-models-1.5.0/es/es-pos-perceptron-autodict01-ancora-2.0.bin");
//            posProperties.setProperty("lemmatizerModel",
//                    "/Users/alessio/Documents/scripts/ixa-pipe-pos/src/main/resources/morph-models-1.5.0/es/es-lemma-perceptron-ancora-2.0.bin");
//            posProperties.setProperty("language", "es");
//            posProperties.setProperty("multiwords", "no");
//            posProperties.setProperty("dictag", "false");
//
//            Annotate annotator = new Annotate(br, tokProperties);
//            KAFDocument document = new KAFDocument("es", "1");
//            annotator.tokenizeToKAF(document);
//
//            eus.ixa.ixa.pipe.pos.Annotate posAnnotator = new eus.ixa.ixa.pipe.pos.Annotate(posProperties);
//            posAnnotator.annotatePOSToKAF(document);
//
//            System.out.println(document);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Annotation document = new Annotation(text);
//        Properties props = new Properties();
//        props.setProperty("annotators", "tokenize, ssplit, pos, ner, parse, fake_lemma, conll_parse, mate");
//        props.setProperty("tokenize.language", "es");
//        props.setProperty("pos.model", "edu/stanford/nlp/models/pos-tagger/spanish/spanish-distsim.tagger");
//        props.setProperty("ner.model", "edu/stanford/nlp/models/ner/spanish.ancora.distsim.s512.crf.ser.gz");
//        props.setProperty("ner.applyNumericClassifiers", "false");
//        props.setProperty("ner.useSUTime", "false");
//        props.setProperty("parse.model", "edu/stanford/nlp/models/lexparser/spanishPCFG.ser.gz");
//
//        props.setProperty("customAnnotatorClass.conll_parse",
//                "eu.fbk.dkm.pikes.tintop.annotators.AnnaParseAnnotator");
//        props.setProperty("customAnnotatorClass.mate", "eu.fbk.dkm.pikes.tintop.annotators.MateSrlAnnotator");
//        props.setProperty("customAnnotatorClass.fake_lemma", "eu.fbk.dkm.pikes.tintop.annotators.FakeLemmaAnnotator");
//
//        props.setProperty("conll_parse.model",
//                "/Users/alessio/Documents/Resources/spanish/CoNLL2009-ST-Spanish-ALL.anna-3.3.parser.model");
//        props.setProperty("conll_parse.language", "es");
//        props.setProperty("mate.model", "/Users/alessio/Documents/Resources/spanish/srl-spa.model");
//
//        StanfordCoreNLP corenlp = new StanfordCoreNLP(props);
//        corenlp.annotate(document);
//
//        for (CoreMap coreMap : document.get(CoreAnnotations.SentencesAnnotation.class)) {
//            List<CoreLabel> tokens = coreMap.get(CoreAnnotations.TokensAnnotation.class);
//            for (CoreLabel token : tokens) {
//                System.out.println("Token: " + token);
//                System.out.println("Index: " + token.index());
//                System.out.println("Sent index: " + token.sentIndex());
//                System.out.println("Begin: " + token.get(CoreAnnotations.CharacterOffsetBeginAnnotation.class));
//                System.out.println("End: " + token.get(CoreAnnotations.CharacterOffsetEndAnnotation.class));
//                System.out.println("POS: " + token.get(CoreAnnotations.PartOfSpeechAnnotation.class));
//                System.out.println("Lemma: " + token.get(CoreAnnotations.LemmaAnnotation.class));
//                System.out.println("NER: " + token.get(CoreAnnotations.NamedEntityTagAnnotation.class));
//                System.out.println("Parse: " + token.get(CoreAnnotations.CoNLLDepTypeAnnotation.class));
//                System.out.println("Parse parent: " + token.get(CoreAnnotations.CoNLLDepParentIndexAnnotation.class));
//                System.out.println("SRL: " + token.get(PikesAnnotations.MateTokenAnnotation.class));
//                System.out.println();
//            }
//        }
    }
}
