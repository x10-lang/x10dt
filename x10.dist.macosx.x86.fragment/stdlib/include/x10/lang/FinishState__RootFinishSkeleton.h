#ifndef __X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H
#define __X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE_H_NODEPS
#include <x10/lang/FinishState.h>
#undef X10_LANG_FINISHSTATE_H_NODEPS
#define X10_LANG_RUNTIME__MORTAL_H_NODEPS
#include <x10/lang/Runtime__Mortal.h>
#undef X10_LANG_RUNTIME__MORTAL_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 

class FinishState__RootFinishSkeleton : public x10::lang::FinishState   {
    public:
    RTT_H_DECLS_CLASS
    
    virtual x10_boolean _isMortal() { return true; }
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > FMGL(xxxx);
    
    virtual x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref(
      );
    virtual void notifyActivityCreation();
    virtual x10aux::ref<x10::lang::FinishState__RootFinishSkeleton> x10__lang__FinishState__RootFinishSkeleton____x10__lang__FinishState__RootFinishSkeleton__this(
      );
    void _constructor();
    
    void __fieldInitializers1927();
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H

namespace x10 { namespace lang { 
class FinishState__RootFinishSkeleton;
} } 

#ifndef X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#define X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState.h>
#include <x10/lang/Runtime__Mortal.h>
#include <x10/lang/GlobalRef.h>
#ifndef X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_GENERICS
#define X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_GENERICS
#endif // X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__ROOTFINISHSKELETON_H_NODEPS
