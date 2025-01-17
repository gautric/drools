/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.drools.mvel.integrationtests.phreak;

import org.drools.core.common.InternalFactHandle;
import org.drools.kiesession.rulebase.InternalKnowledgeBase;
import org.drools.kiesession.rulebase.KnowledgeBaseFactory;
import org.junit.Test;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

// TODO: EM Need to migrate this to executable model
public class PhreakLiaNodeTest {

    @Test
    public void test() {
        String str = "package org.drools.mvel.compiler.test\n" +
                "\n" +
                "import " + A.class.getCanonicalName() + "\n" +
                "import " + B.class.getCanonicalName() + "\n" +
                "\n" +
                "rule r1 \n" +
                "    when \n" +
                "        $a : A( object == 1 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n" +
                "rule r2 \n" +
                "    when \n" +
                "        $a : A( object == 2 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n " +
                "rule r3 \n" +
                "    when \n" +
                "        $a : A( object == 2 )\n" +
                "        $b : B( )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n " +                
                "rule r4 \n" +
                "    when \n" +
                "        $a : A( object == 3 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n";
                
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        builder.add( ResourceFactory.newByteArrayResource(str.getBytes()), ResourceType.DRL);

        if ( builder.hasErrors() ) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());

        KieSession ksession = knowledgeBase.newKieSession();

        InternalFactHandle fhB = ( InternalFactHandle ) ksession.insert( B.b(1) );
        
        InternalFactHandle fhA = ( InternalFactHandle ) ksession.insert( A.a(1) );
        ksession.fireAllRules();        
        System.out.println( "---1---" );
        
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();
//        System.out.println( "---2---" );
//
        ksession.update( fhA, A.a(2) );
        ksession.fireAllRules(); 
        System.out.println( "---3---" );

        ksession.update( fhA, A.a(2) );
        ksession.fireAllRules();
        System.out.println( "---4---" );
        
        ksession.update( fhA, A.a(3) );
        ksession.fireAllRules(); 
        System.out.println( "---5---" );
        
        ksession.update( fhB, B.b(1) );

        ksession.update( fhA, A.a(3) );
        ksession.fireAllRules();  
        
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();        
//
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();          
        
        ksession.dispose();        
    }
    
    @Test
    public void test2() {
        String str = "package org.drools.mvel.compiler.test\n" +
                "\n" +
                "import " + A.class.getCanonicalName() + "\n" +
                "import " + B.class.getCanonicalName() + "\n" +
                "\n" +
                "rule r1 \n" +
                "    when \n" +
                "        $a : A( object == 1 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n" +
                "rule r2 \n" +
                "    when \n" +
                "        $a : A( object == 2 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n " +
                "rule r3 \n" +
                "    when \n" +
                "        $a : A( object == 2 )\n" +
                "        $b : B( )\n" +
                "    then \n" +
                "        System.out.println( $a + \" : \" + $b  );"
                + "      modify($a) { setObject(3) };  \n" +
                "end \n " +                
                "rule r4 \n" +
                "    when \n" +
                "        $a : A( object == 3 )\n" +
                "    then \n" +
                "        System.out.println( $a ); \n" +
                "end \n";
                
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        builder.add( ResourceFactory.newByteArrayResource( str.getBytes() ), ResourceType.DRL);

        if ( builder.hasErrors() ) {
            throw new RuntimeException(builder.getErrors().toString());
        }
        
        InternalKnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addPackages(builder.getKnowledgePackages());

        KieSession ksession = knowledgeBase.newKieSession();

        InternalFactHandle fhB = ( InternalFactHandle ) ksession.insert( B.b(1) );
        
        InternalFactHandle fhA = ( InternalFactHandle ) ksession.insert( A.a(1) );
        ksession.fireAllRules();        
        System.out.println( "---1---" );
        
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();
//        System.out.println( "---2---" );
//
        
        InternalFactHandle fhB2 = ( InternalFactHandle ) ksession.insert( B.b(2) );
        InternalFactHandle fhB3 = ( InternalFactHandle ) ksession.insert( B.b(3) );
        
        ksession.update( fhA, A.a(2) );
        ksession.fireAllRules(); 
        System.out.println( "---3---" );

//        ksession.update( fhA, a(2) );
//        ksession.fireAllRules();
//        System.out.println( "---4---" );
//        
//        ksession.update( fhA, a(3) );
//        ksession.fireAllRules(); 
//        System.out.println( "---5---" );
//        
//        ksession.update( fhB, b(1) );
//
//        ksession.update( fhA, a(3) );
//        ksession.fireAllRules();  
        
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();        
//
//        ksession.update( fhA, a(1) );
//        ksession.fireAllRules();          
        
        ksession.dispose();        
    }    

}
