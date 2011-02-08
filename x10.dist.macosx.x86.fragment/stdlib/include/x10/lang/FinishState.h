#ifndef __X10_LANG_FINISHSTATE_H
#define __X10_LANG_FINISHSTATE_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
namespace x10 { namespace lang { 
class Place;
} } 
#include <x10/lang/Place.struct_h>
namespace x10 { namespace lang { 
class Throwable;
} } 
namespace x10 { namespace lang { 
class SimpleLatch;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
class FinishState__UncountedFinish;
} } 
namespace x10 { namespace lang { 

class FinishState : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual void notifySubActivitySpawn(x10::lang::Place place) = 0;
    virtual void notifyActivityCreation() = 0;
    virtual void notifyActivityTermination() = 0;
    virtual void pushException(x10aux::ref<x10::lang::Throwable> t) = 0;
    virtual void waitForFinish() = 0;
    virtual x10aux::ref<x10::lang::SimpleLatch> simpleLatch() = 0;
    template<class FMGL(T)> static FMGL(T) deref(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > root);
    static x10aux::ref<x10::lang::FinishState__UncountedFinish> FMGL(UNCOUNTED_FINISH);
    
    static void FMGL(UNCOUNTED_FINISH__do_init)();
    static void FMGL(UNCOUNTED_FINISH__init)();
    static volatile x10aux::status FMGL(UNCOUNTED_FINISH__status);
    static inline x10aux::ref<x10::lang::FinishState__UncountedFinish> FMGL(UNCOUNTED_FINISH__get)() {
        if (FMGL(UNCOUNTED_FINISH__status) != x10aux::INITIALIZED) {
            FMGL(UNCOUNTED_FINISH__init)();
        }
        return x10::lang::FinishState::FMGL(UNCOUNTED_FINISH);
    }
    static x10aux::ref<x10::lang::Reference> FMGL(UNCOUNTED_FINISH__deserialize)(x10aux::deserialization_buffer &buf);
    static const x10aux::serialization_id_t FMGL(UNCOUNTED_FINISH__id);
    
    virtual x10aux::ref<x10::lang::FinishState> x10__lang__FinishState____x10__lang__FinishState__this(
      );
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE_H

namespace x10 { namespace lang { 
class FinishState;
} } 

#ifndef X10_LANG_FINISHSTATE_H_NODEPS
#define X10_LANG_FINISHSTATE_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Place.h>
#include <x10/lang/Throwable.h>
#include <x10/lang/SimpleLatch.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/FinishState__UncountedFinish.h>
#ifndef X10_LANG_FINISHSTATE_H_GENERICS
#define X10_LANG_FINISHSTATE_H_GENERICS
#ifndef X10_LANG_FINISHSTATE_H_deref_1150
#define X10_LANG_FINISHSTATE_H_deref_1150
template<class FMGL(T)> FMGL(T) x10::lang::FinishState::deref(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > root) {
    
    //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return x10aux::class_cast<FMGL(T)>(((__extension__ ({
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > __desugarer__var__66__ =
          root;
        
        //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > __var1151__;
        
        //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Labeled_c
        goto __ret2323; __ret2323: 
        //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Do_c
        do
        {
        {
            
            //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
            if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__66__)->location),
                                         x10::lang::Place_methods::_make(x10aux::here)))))
            {
                
                //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Throw_c
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.lang.FinishState]{self.home==here}"))));
            }
            
            //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
            __var1151__ =
              __desugarer__var__66__;
            
            //#line 31 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Branch_c
            goto __ret2323_end_;
        }
        goto __ret2323_next_; __ret2323_next_: ;
        }
        while (false);
        goto __ret2323_end_; __ret2323_end_: ;
        __var1151__;
    }))
    )->__apply());
    
}
#endif // X10_LANG_FINISHSTATE_H_deref_1150
#endif // X10_LANG_FINISHSTATE_H_GENERICS
#endif // __X10_LANG_FINISHSTATE_H_NODEPS
