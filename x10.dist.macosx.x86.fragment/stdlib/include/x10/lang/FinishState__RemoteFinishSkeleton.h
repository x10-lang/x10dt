#ifndef __X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H
#define __X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE_H_NODEPS
#include <x10/lang/FinishState.h>
#undef X10_LANG_FINISHSTATE_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class SimpleLatch;
} } 
namespace x10 { namespace lang { 

class FinishState__RemoteFinishSkeleton : public x10::lang::FinishState   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > FMGL(xxxx);
    
    void _constructor(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > root);
    
    virtual x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > ref(
      );
    virtual void waitForFinish();
    virtual x10aux::ref<x10::lang::SimpleLatch> simpleLatch();
    virtual x10aux::ref<x10::lang::FinishState__RemoteFinishSkeleton> x10__lang__FinishState__RemoteFinishSkeleton____x10__lang__FinishState__RemoteFinishSkeleton__this(
      );
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};

} } 
#endif // X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H

namespace x10 { namespace lang { 
class FinishState__RemoteFinishSkeleton;
} } 

#ifndef X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_NODEPS
#define X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_NODEPS
#include <x10/lang/FinishState.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/SimpleLatch.h>
#ifndef X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_GENERICS
#define X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_GENERICS
#endif // X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_GENERICS
#endif // __X10_LANG_FINISHSTATE__REMOTEFINISHSKELETON_H_NODEPS
